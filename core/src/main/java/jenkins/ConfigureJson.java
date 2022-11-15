package jenkins;

import hudson.Extension;
import hudson.Functions;
import hudson.model.Descriptor;
import hudson.model.RootAction;
import hudson.util.HttpResponses;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import jenkins.model.GlobalConfigurationCategory;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.kohsuke.MetaInfServices;
import org.kohsuke.stapler.Facet;
import org.kohsuke.stapler.HttpResponse;
import org.kohsuke.stapler.WebMethod;
import org.kohsuke.stapler.jelly.JellyFacet;
import org.kohsuke.stapler.verb.GET;

@Extension
@MetaInfServices(Facet.class)
public class ConfigureJson extends JellyFacet implements RootAction {
    private static final Logger LOGGER = Logger.getLogger(ConfigureJson.class.getName());

    @Override
    public String getIconFileName() {
        return null;
    }

    @Override
    public String getDisplayName() {
        return null;
    }

    @Override
    public String getUrlName() {
        return "configure";
    }

    @GET
    @WebMethod(name = "list")
    public HttpResponse doList() {
        // Collection<Descriptor> editable =
        // Functions.getSortedDescriptorsForGlobalConfigUnclassified();
        Collection<Descriptor> items = Functions.getSortedDescriptorsForGlobalConfigUnclassifiedReadable();
        List<JSONObject> response = new ArrayList<>();

        items.forEach(descriptor -> {
            JSONObject o = new JSONObject();
            JSONObject category = new JSONObject();
            GlobalConfigurationCategory cat = descriptor.getCategory();
            category.put("canonical-class", cat.getClass().getCanonicalName());
            category.put("name", cat.getDisplayName());
            category.put("description", cat.getShortDescription());
            o.put("name", descriptor.getDisplayName());
            o.put("url", descriptor.getDescriptorUrl());
            o.put("class-prefix", descriptor.getJsonSafeClassName());
            o.put("config-file", descriptor.getConfigPage());
            o.put("help-file", descriptor.getHelpFile());
            o.put("category", category);
            o.put("global-config", descriptor.getGlobalConfigPage());
            LOGGER.info(o.toString());
            response.add(o);
        });
        return HttpResponses.okJSON(JSONArray.fromObject(response));
    }

    @Override
    public Collection<String> getScriptExtensions() {
        return EXTENSION;
    }

    @Override
    protected String getExtensionSuffix() {
        return EXT;
    }

    private static final String EXT = ".wc";

    private static final Set<String> EXTENSION = Set.of(EXT);

}
