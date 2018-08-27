import com.es.phoneshop.model.ArrayListProductDao;
import com.es.phoneshop.model.Product;
import com.es.phoneshop.model.ProductDao;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Locale;

import static org.junit.Assert.*;

public class ArrayListProductDaoTest {
    private ProductDao productDao = ArrayListProductDao.getInstance();

    @Test
    public void GetInstance() {
        productDao.save(new Product(1L, "a001", "first (null) product",
                new BigDecimal("1.00"), Currency.getInstance(Locale.US), 0));
        Long productId = 1L;
        Product product = productDao.getProduct(productId);
        assertEquals(productId, product.getId());
    }

    @Test
    public void Save() {
        productDao.save(new Product(1L, "a001", "first (null) product",
                new BigDecimal("1.00"), Currency.getInstance(Locale.US), 0));
        long productId = 1L;
        assertNotNull(productDao.getProduct(productId));
    }

    @Test
    public void GetProduct() {
        productDao.save(new Product(2L, "a002", "second product",
                new BigDecimal("6.95"), Currency.getInstance(Locale.US), 100));
        long productId = 2L;
        assertNotNull(productDao.getProduct(productId));
    }

    @Test
    public void FindProducts() {
        assertTrue(productDao.findProducts().isEmpty());
        productDao.save(new Product(2L, "a002", "second product",
                new BigDecimal("6.95"), Currency.getInstance(Locale.US), 100));
        assertFalse(productDao.findProducts().isEmpty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void Remove() {
        productDao.save(new Product(1L, "a001", "first (null) product",
                new BigDecimal("1.00"), Currency.getInstance(Locale.US), 0));
        long productId = 1L;
        productDao.remove(productId);
        assertNull(productDao.getProduct(productId));
    }
}