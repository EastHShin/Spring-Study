package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ItemServiceTest {

    @Autowired private ItemService itemService;
    @Autowired private ItemRepository itemRepository;

    @Test
    public void 아이템_저장() throws Exception{
        //given
        Item item = new Book();
        item.setName("jpa");

        //when
        itemService.saveItem(item);
        Item item1 = itemRepository.findOne(1L);
        //then
        Assertions.assertThat(item1.getName()).isEqualTo(item.getName());
    }


}