# minichessclient

## Links:
* Wiki mit allen wichtigen Grundlagen zu Minichess und Grundlage des Workshops (http://wiki.cs.pdx.edu/minichess/)
* Hinweise zur Implementierung von KI Ansätzen (http://wiki.cs.pdx.edu/mc-howto/)
## Aufbau der Codebase:
### de.pki.minichessclient
* GameState hält alle aktuellen Informationen zum Spielstand (Spielfeld, Spieler am Zug, Runde)
* Game hält die main-Funktion um ein Spiel zu starten
* Entities
    * Move hält einen Zug mit Start- und Ziel-Koordinaten
    * Square hält die x und y Position
* Klassen, die Spieler repräsentieren
    * PlayerRandom zufalls KI Spieler
    * PlayerHuman menschlischer Spieler
* PieceUtil Hilfsklasse zum ermitteln der Spielerfarbe eines Spielsteins
* MoveService Serviceklasse zum ermitteln und validieren von Spielzügen
* Color Enum der Farben
### de.pki.minichessclient.connector
* Klassen im eine Verbindung mit einem imcs (Minischachserverimplementierung) herstellen zu können
