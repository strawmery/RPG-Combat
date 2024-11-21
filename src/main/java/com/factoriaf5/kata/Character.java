package com.factoriaf5.kata;

import java.time.chrono.ThaiBuddhistChronology;

public class Character {
    private String name;
    private int health;
    private int level;
    private int attackRange;
    private boolean isMelee;

    public Character(String name, boolean isMelee) {
        this.name = name;
        this.health = 1000;
        this.level = 1;
        this.isMelee = isMelee;
        this.attackRange = isMelee ? 2 : 20;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getAttackRange() {
        return attackRange;
    }

    public boolean isMelee() {
        return isMelee;
    }

    public boolean isAlive() {
        return this.health > 0;
    }

    public void takeDamage(int damage, Character attacker, int distance) {
        if (this == attacker) {
            return; // Un personaje no puede dañarse a sí mismo
        }

        if (distance > attacker.getAttackRange()) {
            return; // El objetivo está fuera del rango de ataque
        }

        int actualDamage = calculateDamage(damage, attacker);
        this.health = Math.max(0, this.health - actualDamage);
    }

    private int calculateDamage(int damage, Character attacker) {
        int levelDifference = this.level - attacker.getLevel();
        if (levelDifference >= 5) {
            return damage / 2; // 50% de reducción de daño
        } else if (levelDifference <= -5) {
            return damage * 3 / 2; // 50% de aumento de daño
        }
        return damage;
    }

    public void heal(int healAmount, Character healer) {
        if (this != healer) {
            return; // Un personaje solo puede curarse a sí mismo
        }

        if (isAlive()) {
            this.health = Math.min(1000, this.health + healAmount);
        }
    }
}
