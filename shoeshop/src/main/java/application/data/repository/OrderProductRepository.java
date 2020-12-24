package application.data.repository;

import application.data.model.Category;
import application.data.model.Order;
import application.data.model.OrderProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderProductRepository extends JpaRepository<OrderProduct,Integer> {
    @Query("select count(p.id) from dbo_order p")
    long getTotalOrder();



}
