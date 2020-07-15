Nel mondo di futon esiste un gioco molto divertente
chiamato serra-mente. E' un gioco a turni dove si sfidano
due robottini.
Ogni robot ha:
    - un nome;
    - un attacco base;
    - una difesa base;
    - delle mosse conosciute;
    - stato di salute;
Le mosse possono:
    - provocare danni: tolgono punti vita all'avversario seguendo
        questa formula: punti_vista - ((attacco_mossa + attacco base) - difesa_avversario)
    - alterare attacco o difesa: decremento delle statistiche base del personaggio.
        (opzionale) hanno una probabilità di fallire.

Il gioco è un sistema a turni, quindi ogni robot può
effettuare una mossa per turno. Il gioco finisce quando
uno dei due robot è esausto.
Un turno è definito da:
- scelta mossa; (la mossa può essere scelta in base al nome)
- risultato della mossa effettuata;
- cambio turno;

creare un giochino che permetta:
    - di definire le mosse;
    - di definire un robot;
    - effettuare un turno di gioco;
    - effettuare un intero gioco;
