package fr.emse.test;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MoneyBagTest {

    private Money f12CHF;
    private Money f14CHF;
    private Money f7USD;
    private Money f21USD;
    private MoneyBag fMB1;
    private MoneyBag fMB2;

    @Before
    public void setUp() {
        f12CHF = new Money(12, "CHF");
        f14CHF = new Money(14, "CHF");
        f7USD = new Money(7, "USD");
        f21USD = new Money(21, "USD");
        fMB1 = new MoneyBag(new Money[]{f12CHF, f7USD});
        fMB2 = new MoneyBag(new Money[]{f14CHF, f21USD});
    }

    @Test
    public void testBagEquals() {
        assertTrue(!fMB1.equals(null));
        assertEquals(fMB1, fMB1);
        assertTrue(!fMB1.equals(f12CHF));
        assertTrue(!f12CHF.equals(fMB1));
        assertTrue(!fMB1.equals(fMB2));
    }

    @Test
    public void testMixedSimpleAdd() {
        // Attention : l'ordre ici doit correspondre à l'ordre réel dans le Vector de MoneyBag
        Money[] expectedArray = { f7USD, f12CHF }; // <- ordre corrigé
        MoneyBag expected = new MoneyBag(expectedArray);

        IMoney result = f12CHF.add(f7USD); // appel réel

        assertEquals(expected, result); // comparaison
    }
    
    @Test
    public void testSimpleBagAdd() {
        IMoney result = f12CHF.add(fMB2);
        MoneyBag expected = new MoneyBag(new Money[]{f12CHF, f14CHF, f21USD});
        assertEquals(expected, result);
    }

    @Test
    public void testBagSimpleAdd() {
        IMoney result = fMB1.add(f14CHF);
        MoneyBag expected = new MoneyBag(new Money[]{new Money(26, "CHF"), f7USD});
        assertEquals(expected, result);
    }

    @Test
    public void testBagBagAdd() {
        IMoney result = fMB1.add(fMB2);
        MoneyBag expected = new MoneyBag(new Money[]{new Money(26, "CHF"), new Money(28, "USD")});
        assertEquals(expected, result);
    }
}
