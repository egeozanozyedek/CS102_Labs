package cardgame;

// ScoreCard - Maintains one integer score per player, for any number of players
//             Caution: invalid playernumbers result in run-time exception!
// author: Ege Ozan �zyedek
// date: 20.02.2018
public class ScoreCard
{
 // properties
 int[] scores;

 // constructors
 public ScoreCard( int noOfPlayers )
 {
  scores = new int[ noOfPlayers ];

  // init all scores to zero
  for ( int i = 0; i < scores.length; i++ )
   scores[ i ] = 0;
 }

 // methods
 public int getScore( int playerNo )
 {
  return scores[ playerNo ];
 }

 public void update( int playerNo, int amount)
 {
  scores[ playerNo ] += amount;
 }

 public String toString()
 {
  String s;
  int shownNo;
  s = "\n"
    + "_____________\n"
    + "\nPlayer\tScore\n"
    + "_____________\n";

  for ( int playerNo = 0; playerNo < scores.length; playerNo++ )
  {
   shownNo = playerNo + 1;
   s = s + shownNo + "\t" + scores[ playerNo ] + "\n";
  }

  s += "_____________\n";
  return s;
 }

 public int[] getWinners()
 {
  int max;
  int win;
  int[] winners;
  int count;

  count = 0;
  winners = new int[ scores.length ];
  max = scores[ 0 ];
  for ( int i = 0; i < scores.length; i++ ) {
   if ( scores[i] > max )
    max = scores[ i ];
  }

  for ( int i = 0; i < scores.length; i++ ) {
   if ( scores[ i ] == max )
    count++;
  }

  winners = new int[ count ];

  win = 0;
  for ( int i = 0; i < scores.length; i++ ) {
   if ( scores[ i ] == max ) {
    winners[ win ] = i;
    win++;
   }
  }

  return winners;
 }

} // end class ScoreCard
