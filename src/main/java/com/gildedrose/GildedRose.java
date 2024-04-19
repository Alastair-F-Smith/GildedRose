package com.gildedrose;

class GildedRose {
    Item[] items;

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
        return item.name.equals("Sulfuras, Hand of Ragnaros");
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
        return item.name.equals("Aged Brie") || isValidBackstagePass(item);
    }

    private boolean isValidBackstagePass(Item item) {
        return item.name.equals("Backstage passes to a TAFKAL80ETC concert") && item.sellIn >= 0;
    }

    private void increaseQuality(Item item) {
        int increaseAmount = determineIncreaseAmount(item);
        item.quality = Math.min(50, item.quality + increaseAmount);
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
        if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
            item.quality = 0;
        } else {
            int decrease = determineDecreaseAmount(item);
            item.quality = Math.max(0, item.quality - decrease);
        }
    }

    private int determineDecreaseAmount(Item item) {
        return item.sellIn < 0 ? 2 : 1;
    }
}
