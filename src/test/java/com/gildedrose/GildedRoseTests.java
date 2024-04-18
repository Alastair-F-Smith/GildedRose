package com.gildedrose;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    void foo() {
        Item[] items = new Item[] { new Item("foo", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("foo", app.items[0].name);
    }

    @Test
    @DisplayName("Given a regular item, updateQuality decreases quality by 1")
    void givenARegularItemUpdateQualityDecreasesQualityBy1() {
        Item[] items = new Item[] { new Item("foo", 10, 10) };
        GildedRose app = new GildedRose(items);
        int expected = 9;
        app.updateQuality();
        assertEquals(expected, app.items[0].quality);
    }

    @Test
    @DisplayName("Given a regular item, updateQuality decreases SellIn by 1")
    void givenARegularItemUpdateQualityDecreasesSellInBy1() {
        Item[] items = new Item[] { new Item("foo", 5, 10) };
        GildedRose app = new GildedRose(items);
        int expected = 4;
        app.updateQuality();
        assertEquals(expected, app.items[0].sellIn);
    }

    @Test
    @DisplayName("Given a regular item that is past its sell by date, updateQuality decreases its quality by 2")
    void givenARegularItemThatIsPastItsSellByDateUpdateQualityDecreasesItsQualityBy2() {
        Item[] items = new Item[] { new Item("foo", 0, 10) };
        GildedRose app = new GildedRose(items);
        int expected = 8;
        app.updateQuality();
        assertEquals(expected, app.items[0].quality);
    }

    @Test
    @DisplayName("Given a regular item with 0 quality, updateQuality leaves quality unchanged")
    void givenARegularItemWith0QualityUpdateQualityLeavesQualityUnchanged() {
        Item[] items = new Item[] { new Item("foo", 10, 0) };
        GildedRose app = new GildedRose(items);
        int expected = 0;
        app.updateQuality();
        assertEquals(expected, app.items[0].quality);
    }

    @Test
    @DisplayName("Given Aged Brie, updateQuality increases the quality by 1")
    void givenAgedBrieUpdateQualityIncreasesTheQualityBy1() {
        Item[] items = new Item[] { new Item("Aged Brie", 10, 10) };
        GildedRose app = new GildedRose(items);
        int expected = 11;
        app.updateQuality();
        assertEquals(expected, app.items[0].quality);
    }

    @Test
    @DisplayName("Given Aged Brie with a quality of 50, updateQuality leaves quality unchanged")
    void givenAgedBrieWithAQualityOf50UpdateQualityLeavesQualityUnchanged() {
        Item[] items = new Item[] { new Item("Aged Brie", 10, 50) };
        GildedRose app = new GildedRose(items);
        int expected = 50;
        app.updateQuality();
        assertEquals(expected, app.items[0].quality);
    }

    @Test
    @DisplayName("Given Sulfuras, updateQuality leaves quality unchanged")
    void givenSulfurasUpdateQualityLeavesQualityUnchanged() {
        Item[] items = new Item[] { new Item("Sulfuras, Hand of Ragnaros", 10, 10) };
        GildedRose app = new GildedRose(items);
        int expected = 10;
        app.updateQuality();
        assertEquals(expected, app.items[0].quality);
    }

    @Test
    @DisplayName("Given Sulfuras, updateQuality leaves sell by date unchanged")
    void givenSulfurasUpdateQualityLeavesSellByDateUnchanged() {
        Item[] items = new Item[] { new Item("Sulfuras, Hand of Ragnaros", 10, 10) };
        GildedRose app = new GildedRose(items);
        int expected = 10;
        app.updateQuality();
        assertEquals(expected, app.items[0].sellIn);
    }

    @Test
    @DisplayName("Given a backstage pass with more than 10 days, updateQuality increases quality by 1")
    void givenABackstagePassWithMoreThan10DaysUpdateQualityIncreasesQualityBy1() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 11, 10) };
        GildedRose app = new GildedRose(items);
        int expected = 11;
        app.updateQuality();
        assertEquals(expected, app.items[0].quality);
    }

    @Test
    @DisplayName("Given a backstage pass with less than 10 days, updateQuality increases quality by 2")
    void givenABackstagePassWithLessThan10DaysUpdateQualityIncreasesQualityBy2() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 10, 10) };
        GildedRose app = new GildedRose(items);
        int expected = 12;
        app.updateQuality();
        assertEquals(expected, app.items[0].quality);
    }

    @Test
    @DisplayName("Given a backstage pass with less than 5 days, updateQuality increases quality by 3")
    void givenABackstagePassWithLessThan5DaysUpdateQualityIncreasesQualityBy3() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 5, 10) };
        GildedRose app = new GildedRose(items);
        int expected = 13;
        app.updateQuality();
        assertEquals(expected, app.items[0].quality);
    }

    @Test
    @DisplayName("Given a backstage pass after the concert, updateQuality decreases quality to 0")
    void givenABackstagePassAfterTheConcertUpdateQualityDecreasesQualityTo0() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 0, 10) };
        GildedRose app = new GildedRose(items);
        int expected = 0;
        app.updateQuality();
        assertEquals(expected, app.items[0].quality);
    }

}