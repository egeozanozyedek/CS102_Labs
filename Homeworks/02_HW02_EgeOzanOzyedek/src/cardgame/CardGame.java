package cardgame;

import java.util.ArrayList;

// Cardgame
// author: Ege Ozan �zyedek
// date: 20.02.2018
public class CardGame
{
 // properties
 Cards             fullPack;
 ArrayList<Player> players;
 ScoreCard         scoreCard;
 Cards[]           cardsOnTable;
 int               roundNo;
 int               turnOfPlayer;

 // constructors
 public CardGame( Player p1, Player p2, Player p3, Player p4)
 {
  scoreCard = new ScoreCard( 4 );

  fullPack = new Cards( true );
  fullPack.shuffle();
  for ( int i = 0; i < 52; i++ ) 
  {
   if ( i % 4 == 0)
    p1.add(fullPack.getTopCard());
   if ( i % 4 == 1)
    p2.add(fullPack.getTopCard());
   if ( i % 4 == 2)
    p3.add(fullPack.getTopCard());
   if ( i % 4 == 3)
    p4.add(fullPack.getTopCard());
  }

  players = new ArrayList<Player>();
  players.add( p1);
  players.add( p2);
  players.add( p3);
  players.add( p4);

  cardsOnTable = new Cards[ players.size() ]; //4 players, 4 piles of cards on table
  
  for ( int i = 0; i < players.size(); i++ ) 
  {
   cardsOnTable[ i ] = new Cards( false );
  }

  roundNo = 1; 
 }

 // methods
 public boolean playTurn( Player p, Card c)
 {
  if ( isTurnOf( p )) 
  {
   System.out.println( "\nCard being played: " + c.toString() );
   cardsOnTable[ turnOfPlayer ].addTopCard(c);

   if ( turnOfPlayer == 3 ) 
   {
    Card[] cardsPlayedInRound; //gets every card from that round from cardsOnTable
    int roundWin;

    cardsPlayedInRound = new Card[ players.size() ];
    
    for ( int i = 0; i < players.size(); i++ ) 
    {
     Card tmp;
     tmp = cardsOnTable[ i ].getTopCard();
     cardsPlayedInRound[ i ] = tmp;
     cardsOnTable[ i ].addTopCard( tmp );
    }
    
    roundWin = 0;
    
    for ( int i = 0; i < cardsPlayedInRound.length; i++ ) 
    {
     if (cardsPlayedInRound[ i ].compareTo(cardsPlayedInRound[ roundWin ]) == 1)
      roundWin = i;
    }
    
    scoreCard.update( roundWin, 1 );
    System.out.println( "\n" + players.get( roundWin ).getName() + " won the round. ");
    roundNo++;
    turnOfPlayer = 0;
   }
   else 
   {
    turnOfPlayer++;
   }
   return true;
  }
  
  else 
  {
   return false;
  }
 }

 public boolean isTurnOf( Player p)
 {
  if ( players.indexOf( p) == turnOfPlayer )
   return true;
  else
   return false;
 }

 public boolean isGameOver()
 {
  if ( roundNo == 14 )
   return true;
  else
   return false;
 }

 public int getScore( int playerNumber)
 {
  return scoreCard.getScore( playerNumber );
 }

 public String getName( int playerNumber)
 {
  return players.get( playerNumber ).getName();
 }

 public int getRoundNo()
 {
  return roundNo;
 }

 public int getTurnOfPlayerNo()
 {
  return turnOfPlayer + 1; // + 1 because it looks better aesthetically
 }

 public Player[] getWinners()
 {
  int[] temp;
  Player[] winners;

  temp = scoreCard.getWinners();
  winners = new Player[temp.length];

  for (int i = 0; i < temp.length; i++) 
  {
   winners[i] = players.get(temp[i]);
  }

  return winners;
 }

 public String showScoreCard()
 {
  return scoreCard.toString();
 }
}