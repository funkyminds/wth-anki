package wth.model.anki.note

case class AddNote(action: String = "addNotes", version: Int = 6, params: NoteParams, option: Options)
