package info.drj.converter;

import info.drj.model.Produto;
import info.drj.repository.Produtos;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import org.apache.commons.lang3.StringUtils;

@FacesConverter(forClass = Produto.class)
public class ProdutoConverter implements Converter {

    @Inject
    private Produtos produtos;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Produto retorno = null;
        if (StringUtils.isNotEmpty(value)) {
            Long id = new Long(value);
            retorno = produtos.porId(id);
        }
        return retorno;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null) {
            Produto produto = (Produto) value;
            return produto.getId() == null ? null : produto.getId().toString();
        }
        return "";
    }
}
