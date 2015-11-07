package org.plazmaforge.framework.uwt.generator.widget;

import org.plazmaforge.framework.core.data.object.IData;
import org.plazmaforge.framework.uwt.generator.GeneratorContext;
import org.plazmaforge.framework.uwt.generator.IUIGenerator;
import org.plazmaforge.framework.uwt.generator.ScopeContext;
import org.plazmaforge.framework.uwt.generator.SourceWriter;
import org.plazmaforge.framework.uwt.widget.Label;

public class LabelGenerator extends ControlGenerator implements IUIGenerator {

    @Override
    public boolean accept(String type) {
	return false;
    }

    @Override
    public String generateClass(GeneratorContext context, IData data) {
	return null;
    }

    @Override
    public String getClassName(String baseType, String type) {
	return getDefaultClassName(baseType, type);
    }

    @Override
    public void generatePopulate(ScopeContext context, IData data, String bean, SourceWriter sw) {
	super.generatePopulate(context, data, bean, sw);
	generateSetRSStringProperty(context, data, bean, Label.PROPERTY_TEXT, sw);
	generateSetImagePathProperty(context, data, bean, Label.PROPERTY_ICON, sw); // image attribute as image path
    }
}
