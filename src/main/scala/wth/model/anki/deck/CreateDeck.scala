package wth.model.anki.deck

case class CreateDeck(action: String = "createDeck", version: Int = 6, params: CreateDeckParams)
