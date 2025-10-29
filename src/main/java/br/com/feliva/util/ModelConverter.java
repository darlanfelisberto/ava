package br.com.feliva.util;

import br.com.feliva.sharedClass.db.Model;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.extern.log4j.Log4j2;

import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
@Log4j2
@Named
@FacesConverter(value = "ds.modelConverter")
public class ModelConverter implements Converter<Model<?>> {

    public static final String SEPARATIOR_KEY = "@";

    static private Map<Integer, Class<?>> classes = new HashMap<Integer, Class<?>>();

    @Inject private ConverterDAO converterDAO;

    @Override
    public Model<?> getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {

        try {
            if(value != null && !value.contains(SEPARATIOR_KEY)) {
                return null;
            }
            String[]name = value.split(SEPARATIOR_KEY);

            Class<?> entity = classes.get(Integer.parseInt(name[0]));

            Model<?> m = (Model<?>) converterDAO.findBd(entity, (Class<?>)entity.getMethod("getMMId").getAnnotatedReturnType().getType(),name[1]);

            return m;
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Model model) {
        if(model == null) {
            return null;
        }
        return model.getClass().getName().hashCode() + SEPARATIOR_KEY + model.getMMId().toString();
    }

    public static void register(Class cla){
        classes.put(cla.getName().hashCode(),cla);
    }
}
