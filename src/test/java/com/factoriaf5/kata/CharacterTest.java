package com.factoriaf5.kata;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CharacterTest {
    
       private Character meleeCharacter;
    private Character rangedCharacter;

    @BeforeEach
    void setUp() {
        meleeCharacter = new Character("Warrior", true);
        rangedCharacter = new Character("Archer", false);
    }

    @Test
    void testInitialState() {
        assertEquals(1000, meleeCharacter.getHealth());
        assertEquals(1, meleeCharacter.getLevel());
        assertTrue(meleeCharacter.isAlive());
        assertEquals(2, meleeCharacter.getAttackRange());
        assertTrue(meleeCharacter.isMelee());

        assertEquals(1000, rangedCharacter.getHealth());
        assertEquals(1, rangedCharacter.getLevel());
        assertTrue(rangedCharacter.isAlive());
        assertEquals(20, rangedCharacter.getAttackRange());
        assertFalse(rangedCharacter.isMelee());
    }

    @Test
    void testTakeDamage() {
        meleeCharacter.takeDamage(200, rangedCharacter, 10);
        assertEquals(800, meleeCharacter.getHealth());
    }

    @Test
    void testTakeDamageOutOfRange() {
        meleeCharacter.takeDamage(200, rangedCharacter, 25);
        assertEquals(1000, meleeCharacter.getHealth());
    }

    @Test
    void testSelfDamage() {
        meleeCharacter.takeDamage(200, meleeCharacter, 1);
        assertEquals(1000, meleeCharacter.getHealth());
    }

    @Test
    void testDeath() {
        meleeCharacter.takeDamage(1100, rangedCharacter, 10);
        assertEquals(0, meleeCharacter.getHealth());
        assertFalse(meleeCharacter.isAlive());
    }

    @Test
    void testHeal() {
        meleeCharacter.takeDamage(200, rangedCharacter, 10);
        meleeCharacter.heal(100, meleeCharacter);
        assertEquals(900, meleeCharacter.getHealth());
    }

    @Test
    void testHealOverMaxHealth() {
        meleeCharacter.takeDamage(100, rangedCharacter, 10);
        meleeCharacter.heal(200, meleeCharacter);
        assertEquals(1000, meleeCharacter.getHealth());
    }

    @Test
    void testHealDeadCharacter() {
        meleeCharacter.takeDamage(1100, rangedCharacter, 10);
        meleeCharacter.heal(100, meleeCharacter);
        assertEquals(0, meleeCharacter.getHealth());
        assertFalse(meleeCharacter.isAlive());
    }

    @Test
    void testHealByOther() {
        meleeCharacter.takeDamage(200, rangedCharacter, 10);
        meleeCharacter.heal(100, rangedCharacter);
        assertEquals(800, meleeCharacter.getHealth());
    }

    @Test
    void testDamageReductionHigherLevel() {
        meleeCharacter.setLevel(10);
        rangedCharacter.setLevel(5);
        meleeCharacter.takeDamage(100, rangedCharacter, 10);
        assertEquals(950, meleeCharacter.getHealth());
    }

    @Test
    void testDamageIncreaseLowerLevel() {
        meleeCharacter.setLevel(5);
        rangedCharacter.setLevel(10);
        meleeCharacter.takeDamage(100, rangedCharacter, 10);
        assertEquals(850, meleeCharacter.getHealth());
    }

}
