package com.gildedrose;

class GildedRose {
    Item[] items;
    private static final int MAX_QUALITY = 50;
    private static final String BACKSTAGE_PASS = "Backstage passes to a TAFKAL80ETC concert";
    private static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    private static final String AGED_BRIE = "Aged Brie";

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            updateItem(item);
        }
    }

    private void updateItem(Item item) {
        if (!isLegendary(item)) {
            updateItemSellByDate(item);
            updateItemQuality(item);
        }
    }

    private boolean isLegendary(Item item) {
        return item.name.equals(SULFURAS);
    }

    private void updateItemSellByDate(Item item) {
        item.sellIn = item.sellIn - 1;
    }

    private void updateItemQuality(Item item) {
        if (isAppreciatingItem(item)) {
            increaseQuality(item);
        } else {
            decreaseQuality(item);
        }
    }

    private boolean isAppreciatingItem(Item item) {
        return item.name.equals(AGED_BRIE) || isValidBackstagePass(item);
    }

    private boolean isValidBackstagePass(Item item) {
        return item.name.equals(BACKSTAGE_PASS) && item.sellIn >= 0;
    }

    private void increaseQuality(Item item) {
        int increaseAmount = determineIncreaseAmount(item);
        item.quality = Math.min(MAX_QUALITY, item.quality + increaseAmount);
    }

    private int determineIncreaseAmount(Item item) {
        return item.name.equals("Aged Brie") ? determineBrieIncrease(item)
                : determineBackstagePassIncrease(item);
    }

    private int determineBrieIncrease(Item item) {
        return item.sellIn < 0 ? 2 : 1;
    }

    private int determineBackstagePassIncrease(Item item) {
        int amount = 1;
        if (item.sellIn < 5) {
            amount = 3;
        } else if (item.sellIn < 10) {
            amount = 2;
        }
        return amount;
    }

    private void decreaseQuality(Item item) {
        if (item.name.equals(BACKSTAGE_PASS)) {
            item.quality = 0;
        } else {
            int decrease = determineDecreaseAmount(item);
            item.quality = Math.max(0, item.quality - decrease);
        }
    }

    private int determineDecreaseAmount(Item item) {
        int decrease = item.sellIn < 0 ? 2 : 1;
        if (isConjured(item)) decrease *= 2;
        return decrease;
    }

    private boolean isConjured(Item item) {
        return item.name.startsWith("Conjured ");
    }
}
