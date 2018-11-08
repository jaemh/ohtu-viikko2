package ohtuesimerkki;


import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class StatisticsTest {
 
    Reader readerStub = new Reader() {
 
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<Player>();
 
            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));
 
            return players;
        }
    };
 
    Statistics stats;

    @Before
    public void setUp(){
        stats = new Statistics(readerStub);
    }  

    @Test
    public void existingPlayerIsFound() {
        Player player = stats.search("Kurri");
        
        assertEquals("Kurri", player.getName());
        assertEquals(37, player.getGoals());
    } 


    @Test
    public void nonExistingplayerIsNotFound() {
        Player player = stats.search("Hellas");
        
        assertEquals(null, player);
    } 
    
    @Test
    public void playersOfTeamFound() {
        List<Player> players = stats.team("EDM");
        List<String> names = players.stream().map(p->p.getName()).collect(Collectors.toList());
        
        assertEquals(3, players.size());      
        assertTrue(names.containsAll(Lists.newArrayList("Kurri", "Semenko", "Gretzky")));
    }
    
    @Test
    public void topFound() {
        List<Player> players = stats.topScorers(2);
        
        assertEquals(2, players.size());  
        assertEquals("Gretzky", players.get(0).getName());
    }
    
}