package com.laohu.repository;

import com.laohu.dataobject.OrderDetil;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by @Author tachai on 2018/1/12.
 *
 * @Email 1206966083@qq.com
 */
public interface OrderDetilRepository extends JpaRepository<OrderDetil,String> {
    List<OrderDetil> findByOAndOrderId(String orderId);
}
