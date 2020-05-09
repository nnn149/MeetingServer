/**
 * FileName: PageUtil
 * Author:   10418
 * Date:     2019-08-21 9:20
 * Description: 分页工具类
 * History:
 * <author>          <time>          <version>          <desc>
 * 楠楠(Nannan))
 */
package cn.nicenan.meeting.util;

import cn.nicenan.meeting.model.dto.LimitDto;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * DESC〈一句话功能简述〉<br>
 * 〈分页工具类〉
 *
 * @author 10418
 * @create 2019-08-21
 * @since 1.0.0
 */
public class PageUtil {
    private final static Logger logger = LoggerFactory.getLogger(PageUtil.class);

    public static <T> Page<T> generatePage(Class<T> tClass, long page, int limit, String[] sorts) {
        Page<T> pagePg = new Page<>(page, limit);
        for (String sort : sorts) {
            if ("+".equals(sort.substring(0, 1))) {
                pagePg.addOrder(OrderItem.asc(sort.substring(1)));
            } else {
                pagePg.addOrder(OrderItem.desc(sort.substring(1)));
            }
        }
        return pagePg;
    }

    public static <T> Page<T> generatePage(Class<T> tClass, LimitDto limitDto) {
        String flag = "+";
        Page<T> pagePg = new Page<>(limitDto.getPage(), limitDto.getLimit());
        String firstSort = limitDto.getSort();
        if (StringUtils.isNotBlank(firstSort)) {
            if (flag.equals(firstSort.substring(0, 1))) {
                pagePg.addOrder(OrderItem.asc(firstSort.substring(1)));
            } else {
                pagePg.addOrder(OrderItem.desc(firstSort.substring(1)));
            }
        }
        if (limitDto.getSorts() != null && limitDto.getSorts().length > 0) {
            for (String sort : limitDto.getSorts()) {
                if (flag.equals(sort.substring(0, 1))) {
                    pagePg.addOrder(OrderItem.asc(sort.substring(1)));
                } else {
                    pagePg.addOrder(OrderItem.desc(sort.substring(1)));
                }
            }
        }
        return pagePg;
    }
}
