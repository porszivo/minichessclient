# minichessclient

## Links:
* Wiki mit allen wichtigen Grundlagen zu Minichess und Grundlage des Workshops (http://wiki.cs.pdx.edu/minichess/)
* Hinweise zur Implementierung von KI Ansätzen (http://wiki.cs.pdx.edu/mc-howto/)
## Aufbau der Codebase:
### de.pki.minichess
* MinichessApplication hält die main-Funktion um ein Spiel zu starten
### de.pki.minichess.game
* State hält alle aktuellen Informationen zum Spielstand (Spielfeld, Spieler am Zug, Runde)
* Entities
    * Move hält einen Zug mit Start- und Ziel-Koordinaten
    * Square hält die x und y Position
* PieceUtil Hilfsklasse zum ermitteln der Spielerfarbe eines Spielsteins
* MoveService Serviceklasse zum ermitteln und validieren von Spielzügen
* Color Enum der Farben
### de.pki.minichess.client
* Klassen im eine Verbindung mit einem imcs (Minischachserverimplementierung) herstellen zu können
### de.pki.minichess.ai
* Klassen, die Spieler repräsentieren
    * PlayerRandom zufalls KI Spieler
