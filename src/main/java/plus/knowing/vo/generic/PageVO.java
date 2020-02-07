package plus.knowing.vo.generic;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import plus.knowing.util.ConvertUtil;

import java.util.Collections;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PageVO<T> {

    private List<T> list = Collections.emptyList();

    private Long total = 0L;

    private Long currentPage = 1L;

    private Long totalPage = 0L;

    private PageVO(Page<T> page) {
        this.total = page.getTotal();
        this.currentPage = page.getCurrent();
        this.totalPage = page.getPages();
        this.list = page.getRecords();
    }

    public PageVO(IPage page, List<T> voList) {
        this.total = page.getTotal();
        this.currentPage = page.getCurrent();
        this.totalPage = page.getPages();
        this.list = voList;
    }

    public static <D, T> PageVO<T> build(IPage<D> page, Class<T> tClass) {
        return new PageVO<>(page, ConvertUtil.convert(page.getRecords(), tClass));
    }
}
