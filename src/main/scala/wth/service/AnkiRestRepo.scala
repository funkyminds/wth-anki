package wth.service

import wth.model.anki._
import wth.model.anki.deck._
import wth.model.anki.note._
import wth.model.http.EntitySerDes
import wth.service.ResponseParser.Translation
import wth.service.TranslationRepo.TranslationRepo
import wth.service.http._
import zio._

object AnkiRestRepo {
  type Configuration = Has[AnkiConfig]

  def service[SerDes[_] <: EntitySerDes[_]: TagK]
    : URLayer[HttpClient.HttpClient[SerDes] with Has[SerDes[AddNote]] with Has[SerDes[CreateDeck]] with Configuration,
              TranslationRepo
    ] =
    ZLayer
      .fromServices[HttpClient.Service[SerDes], SerDes[AddNote], SerDes[
        CreateDeck
      ], AnkiConfig, TranslationRepo.Service] { (client, addNoteSerDes, createDeckSerDes, config) =>
        new TranslationRepo.Service {
          override def persist(translations: Set[(String, String)]): Task[Boolean] = {
            val request: CreateDeck = createDeckRequest
            client.post(request, config.address)(createDeckSerDes) *> super.persist(translations)
          }

          override def persist(translation: Translation): Task[Boolean] = {
            val request = addNoteRequest(translation)
            client.post(request, config.address)(addNoteSerDes)
          }

          def createDeckRequest: CreateDeck =
            CreateDeck(params = CreateDeckParams(config.deck_name))

          def addNoteRequest(translation: Translation): AddNote = {
            val (phrase, meaning) = translation

            AddNote(
              params = NoteParams(
                Seq(
                  Note(
                    deckName = config.deck_name,
                    modelName = "Basic (type in the answer)",
                    fields = Field(meaning, phrase)
                  )
                )
              ),
              option = Options()
            )
          }
        }
      }
}
