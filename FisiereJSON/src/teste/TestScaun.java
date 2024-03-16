package teste;

import p1.Scaun;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestScaun {

    @Test
    public void testConstructorImplicit() {
        Scaun scaun = new Scaun();
        Assertions.assertEquals("necunoscut", scaun.getProducator());
        Assertions.assertEquals("necunoscut", scaun.getMaterial());
        Assertions.assertEquals(0.0, scaun.getPret());
    }

    @Test
    public void testConstructorCuParametri() {
        Scaun scaun = new Scaun("ProducatorTest", "MaterialTest", 50.0);
        Assertions.assertEquals("ProducatorTest", scaun.getProducator());
        Assertions.assertEquals("MaterialTest", scaun.getMaterial());
        Assertions.assertEquals(50.0, scaun.getPret());
    }
    
    @Test
    public void testCompareTo() {
        Scaun scaun1 = new Scaun("ProducatorA", "MaterialA", 300.0);
        Scaun scaun2 = new Scaun("ProducatorB", "MaterialB", 150.0);
        Assertions.assertTrue(scaun1.compareTo(scaun2) > 0);
    }

}
