package br.com.feliva.bean.produto;

import br.com.feliva.erp.model.Produto;
import jakarta.faces.context.FacesContext;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class ProdutoLazyDataModel extends LazyDataModel<Produto> {

    private static final long serialVersionUID = 1L;

    private List<Produto> datasource;

    public ProdutoLazyDataModel(List<Produto> datasource) {
        this.datasource = datasource;
    }

    public Produto getRowData(String rowKey) {
        for (Produto customer : datasource) {
            if (customer.getMMId() == Integer.parseInt(rowKey)) {
                return customer;
            }
        }

        return null;
    }

    @Override
    public String getRowKey(Produto customer) {
        return String.valueOf(customer.getMMId());
    }

    @Override
    public int count(Map<String, FilterMeta> filterBy) {
        return (int) datasource.stream()
                .filter(o -> filter(FacesContext.getCurrentInstance(), filterBy.values(), o))
                .count();
    }

    @Override
    public List<Produto> load(int offset, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) {
        // apply offset & filters
//        List<Customer> customers = datasource.stream()
//                .filter(o -> filter(FacesContext.getCurrentInstance(), filterBy.values(), o))
//                .collect(Collectors.toList());
//
//        // sort
//        if (!sortBy.isEmpty()) {
//            List<Comparator<Customer>> comparators = sortBy.values().stream()
//                    .map(o -> new LazySorter(o.getField(), o.getOrder()))
//                    .collect(Collectors.toList());
//            Comparator<Customer> cp = ComparatorUtils.chainedComparator(comparators); // from apache
//            customers.sort(cp);
//        }

        return null;//customers.subList(offset, Math.min(offset + pageSize, customers.size()));
    }

    private boolean filter(FacesContext context, Collection<FilterMeta> filterBy, Object o) {
        boolean matching = true;

//        for (FilterMeta filter : filterBy) {
//            FilterConstraint constraint = filter.getConstraint();
//            Object filterValue = filter.getFilterValue();
//
//            try {
//                Object columnValue = String.valueOf(ShowcaseUtil.getPropertyValueViaReflection(o, filter.getField()));
//                matching = constraint.isMatching(context, columnValue, filterValue, LocaleUtils.getCurrentLocale());
//            }
//            catch (ReflectiveOperationException | IntrospectionException e) {
//                matching = false;
//            }
//
//            if (!matching) {
//                break;
//            }
//        }

        return matching;
    }

}