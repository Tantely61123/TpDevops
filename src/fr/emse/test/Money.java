package fr.emse.test;

public class Money implements IMoney {
    private int fAmount;
    private String fCurrency;

    // Constructeur
    public Money(int amount, String currency) {
        this.fAmount = amount;
        this.fCurrency = currency;
    }

    // Accesseurs
    public int amount() {
        return fAmount;
    }

    public String currency() {
        return fCurrency;
    }

    // Méthode d'ajout via interface IMoney
    @Override
    public IMoney add(IMoney m) {
        return m.addMoney(this); // Appel inversé pour gérer tous les cas
    }

    // Ajoute un autre Money (même devise ou pas)
    @Override
    public IMoney addMoney(Money m) {
        if (m.currency().equals(this.currency())) {
            return new Money(this.amount() + m.amount(), this.currency());
        }
        return new MoneyBag(this, m); // crée un MoneyBag avec les deux devises
    }

    // Ajoute un MoneyBag à ce Money
    @Override
    public IMoney addMoneyBag(MoneyBag bag) {
        return bag.addMoney(this); // délègue à MoneyBag.addMoney
    }

    // Comparaison logique (égalité de valeur + devise)
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Money)) return false;
        Money other = (Money) obj;
        return this.fAmount == other.fAmount && this.fCurrency.equals(other.fCurrency);
    }

    // Affichage pour debug
    @Override
    public String toString() {
        return fAmount + " " + fCurrency;
    }
}
