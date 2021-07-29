package hello.itemservice.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach() {
        itemRepository.clearStore();
    }

    @Test
    void save() throws Exception {
        // given
        Item item = new Item("itemA", 10000, 10);
        // when
        Item savedItem = itemRepository.save(item);
        // then
        Item foundItem = itemRepository.findById(item.getId());
        assertThat(foundItem).isEqualTo(savedItem);
    }

    @Test
    void findAll() throws Exception {
        // given
        Item item1 = new Item("item1", 10000, 10);
        Item item2 = new Item("item2", 20000, 20);
        itemRepository.save(item1);
        itemRepository.save(item2);

        // when
        List<Item> items = itemRepository.findAll();

        // then
        assertThat(items.size()).isEqualTo(2);
        assertThat(items).contains(item1, item2);
    }

    @Test
    void updateItem() throws Exception {
        // given
        Item item = new Item("item1", 10000, 10);
        Item savedItem = itemRepository.save(item);
        Long itemId = savedItem.getId();
        // when
        Item updateParam = new Item("item2", 20000, 30);
        itemRepository.update(itemId, updateParam);

        // then
        Item foundItem = itemRepository.findById(itemId);
        assertThat(foundItem.getItemName()).isEqualTo(updateParam.getItemName());
        assertThat(foundItem.getPrice()).isEqualTo(updateParam.getPrice());
        assertThat(foundItem.getQuantity()).isEqualTo(updateParam.getQuantity());
    }
}