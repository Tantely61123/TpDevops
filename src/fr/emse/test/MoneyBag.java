package fr.emse.test;

import java.util.Vector;

public class MoneyBag implements IMoney {
    protected Vector<Money> fMonies = new Vector<>();

    // Constructeur avec 2 Money
    public MoneyBag(Money m1, Money m2) {
        appendMoney(m1);
        appendMoney(m2);
    }

    // Constructeur avec tableau
    public MoneyBag(Money[] bag) {
        for (Money m : bag) {
            appendMoney(m);
        }
    }

    // Ajoute ou fusionne la devise
    protected void appendMoney(Money m) {
        for (int i = 0; i < fMonies.size(); i++) {
            Money current = fMonies.get(i);
            if (current.currency().equals(m.currency())) {
                fMonies.set(i, new Money(current.amount() + m.amount(), m.currency()));
                return;
            }
        }
        fMonies.add(m);
    }

    // Convertit le contenu du MoneyBag en tableau
    public Money[] toArray() {
        return fMonies.toArray(new Money[fMonies.size()]);
    }

    // IMoney : ajouter un IMoney (appel inversé pour double dispatch)
    @Override
    public IMoney add(IMoney m) {
        return m.addMoneyBag(this);
    }

    // Ajouter un Money
    @Override
    public IMoney addMoney(Money m) {
        MoneyBag result = new MoneyBag(this.toArray());
        result.appendMoney(m);
        if (result.fMonies.size() == 1)
            return result.fMonies.get(0); // simplification
        return result;
    }

    // Ajouter un autre MoneyBag
    @Override
    public IMoney addMoneyBag(MoneyBag bag) {
        MoneyBag result = new MoneyBag(this.toArray());
        for (Money m : bag.fMonies) {
            result.appendMoney(m);
        }
        if (result.fMonies.size() == 1)
            return result.fMonies.get(0); // simplification
        return result;
    }

    // Égalité logique
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof MoneyBag)) return false;
        MoneyBag other = (MoneyBag) obj;
        return fMonies.equals(other.fMonies);
    }

    // Affichage pour debug
    @Override
    public String toString() {
        return fMonies.toString();
    }
}
