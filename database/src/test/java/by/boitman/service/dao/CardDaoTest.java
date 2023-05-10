package by.boitman.service.dao;

public class CardDaoTest {

    private CardDao cardDao = CardDao.getInstance();

//    @Test
//    public void getAll() {
//        List<Card> result = CardDao.findAll();
//        Assert.assertTrue(result.size() > 0);
//    }
//
//    @Test
//    public void whenTheCardByIdExistsThenCardIsReturned() {
//        Optional<Card> byId = CardDao.findById(1L);
//        Assert.assertTrue(byId.isPresent());
//    }
//
//    @Test
//    public void whenTheCardByIdDoesntExistThenNullIsReturned() {
//        Optional<Card> byId = cardDao.findById(100L);
//        Assert.assertTrue(byId.isEmpty());
//    }

//    @Test
//    public void whenCardPassedThenCardIsCreated() throws SQLException {
//        Optional<Card> test = cardDao.create(Card.builder()
//                .ownerName("owner_name")
//                .ownerSurname("owner_surname")
//                .dateCard("date_card")
//                .cardNumber(Long.valueOf("card_number"))
//                .balance(Double.valueOf("balance"))
//                .build());
//        Assert.assertNotNull(test.get().getId());
//    }
}