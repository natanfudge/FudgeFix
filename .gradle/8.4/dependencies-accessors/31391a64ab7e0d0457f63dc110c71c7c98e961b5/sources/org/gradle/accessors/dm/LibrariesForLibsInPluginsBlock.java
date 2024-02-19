package org.gradle.accessors.dm;

import org.gradle.api.NonNullApi;
import org.gradle.api.artifacts.MinimalExternalModuleDependency;
import org.gradle.plugin.use.PluginDependency;
import org.gradle.api.artifacts.ExternalModuleDependencyBundle;
import org.gradle.api.artifacts.MutableVersionConstraint;
import org.gradle.api.provider.Provider;
import org.gradle.api.model.ObjectFactory;
import org.gradle.api.provider.ProviderFactory;
import org.gradle.api.internal.catalog.AbstractExternalDependencyFactory;
import org.gradle.api.internal.catalog.DefaultVersionCatalog;
import java.util.Map;
import org.gradle.api.internal.attributes.ImmutableAttributesFactory;
import org.gradle.api.internal.artifacts.dsl.CapabilityNotationParser;
import javax.inject.Inject;

/**
 * A catalog of dependencies accessible via the `libs` extension.
 */
@NonNullApi
public class LibrariesForLibsInPluginsBlock extends AbstractExternalDependencyFactory {

    private final AbstractExternalDependencyFactory owner = this;
    private final FabricLibraryAccessors laccForFabricLibraryAccessors = new FabricLibraryAccessors(owner);
    private final VersionAccessors vaccForVersionAccessors = new VersionAccessors(providers, config);
    private final BundleAccessors baccForBundleAccessors = new BundleAccessors(objects, providers, config, attributesFactory, capabilityNotationParser);
    private final PluginAccessors paccForPluginAccessors = new PluginAccessors(providers, config);

    @Inject
    public LibrariesForLibsInPluginsBlock(DefaultVersionCatalog config, ProviderFactory providers, ObjectFactory objects, ImmutableAttributesFactory attributesFactory, CapabilityNotationParser capabilityNotationParser) {
        super(config, providers, objects, attributesFactory, capabilityNotationParser);
    }

        /**
         * Creates a dependency provider for minecraft (com.mojang:minecraft)
         * This dependency was declared in catalog libs.versions.toml
     * @deprecated Will be removed in Gradle 9.0.
         */
    @Deprecated
        public Provider<MinimalExternalModuleDependency> getMinecraft() {
        org.gradle.internal.deprecation.DeprecationLogger.deprecateBehaviour("Accessing libraries or bundles from version catalogs in the plugins block.").withAdvice("Only use versions or plugins from catalogs in the plugins block.").willBeRemovedInGradle9().withUpgradeGuideSection(8, "kotlin_dsl_deprecated_catalogs_plugins_block").nagUser();
            return create("minecraft");
    }

        /**
         * Creates a dependency provider for modmenu (com.terraformersmc:modmenu)
         * This dependency was declared in catalog libs.versions.toml
     * @deprecated Will be removed in Gradle 9.0.
         */
    @Deprecated
        public Provider<MinimalExternalModuleDependency> getModmenu() {
        org.gradle.internal.deprecation.DeprecationLogger.deprecateBehaviour("Accessing libraries or bundles from version catalogs in the plugins block.").withAdvice("Only use versions or plugins from catalogs in the plugins block.").willBeRemovedInGradle9().withUpgradeGuideSection(8, "kotlin_dsl_deprecated_catalogs_plugins_block").nagUser();
            return create("modmenu");
    }

    /**
     * Returns the group of libraries at fabric
     * @deprecated Will be removed in Gradle 9.0.
     */
    @Deprecated
    public FabricLibraryAccessors getFabric() {
        org.gradle.internal.deprecation.DeprecationLogger.deprecateBehaviour("Accessing libraries or bundles from version catalogs in the plugins block.").withAdvice("Only use versions or plugins from catalogs in the plugins block.").willBeRemovedInGradle9().withUpgradeGuideSection(8, "kotlin_dsl_deprecated_catalogs_plugins_block").nagUser();
        return laccForFabricLibraryAccessors;
    }

    /**
     * Returns the group of versions at versions
     */
    public VersionAccessors getVersions() {
        return vaccForVersionAccessors;
    }

    /**
     * Returns the group of bundles at bundles
     * @deprecated Will be removed in Gradle 9.0.
     */
    @Deprecated
    public BundleAccessors getBundles() {
        org.gradle.internal.deprecation.DeprecationLogger.deprecateBehaviour("Accessing libraries or bundles from version catalogs in the plugins block.").withAdvice("Only use versions or plugins from catalogs in the plugins block.").willBeRemovedInGradle9().withUpgradeGuideSection(8, "kotlin_dsl_deprecated_catalogs_plugins_block").nagUser();
        return baccForBundleAccessors;
    }

    /**
     * Returns the group of plugins at plugins
     */
    public PluginAccessors getPlugins() {
        return paccForPluginAccessors;
    }

    /**
     * @deprecated Will be removed in Gradle 9.0.
     */
    @Deprecated
    public static class FabricLibraryAccessors extends SubDependencyFactory {

        public FabricLibraryAccessors(AbstractExternalDependencyFactory owner) { super(owner); }

            /**
             * Creates a dependency provider for loader (net.fabricmc:fabric-loader)
             * This dependency was declared in catalog libs.versions.toml
         * @deprecated Will be removed in Gradle 9.0.
             */
        @Deprecated
            public Provider<MinimalExternalModuleDependency> getLoader() {
            org.gradle.internal.deprecation.DeprecationLogger.deprecateBehaviour("Accessing libraries or bundles from version catalogs in the plugins block.").withAdvice("Only use versions or plugins from catalogs in the plugins block.").willBeRemovedInGradle9().withUpgradeGuideSection(8, "kotlin_dsl_deprecated_catalogs_plugins_block").nagUser();
                return create("fabric.loader");
        }

    }

    public static class VersionAccessors extends VersionFactory  {

        private final ArchitecturyVersionAccessors vaccForArchitecturyVersionAccessors = new ArchitecturyVersionAccessors(providers, config);
        private final FabricVersionAccessors vaccForFabricVersionAccessors = new FabricVersionAccessors(providers, config);
        private final ModVersionAccessors vaccForModVersionAccessors = new ModVersionAccessors(providers, config);
        private final ModmussVersionAccessors vaccForModmussVersionAccessors = new ModmussVersionAccessors(providers, config);
        private final OverridesVersionAccessors vaccForOverridesVersionAccessors = new OverridesVersionAccessors(providers, config);
        private final QuiltVersionAccessors vaccForQuiltVersionAccessors = new QuiltVersionAccessors(providers, config);
        private final ReleaseVersionAccessors vaccForReleaseVersionAccessors = new ReleaseVersionAccessors(providers, config);
        private final YarnVersionAccessors vaccForYarnVersionAccessors = new YarnVersionAccessors(providers, config);
        public VersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: forge (49.0.30)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getForge() { return getVersion("forge"); }

            /**
             * Returns the version associated to this alias: minecraft (1.20.4)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getMinecraft() { return getVersion("minecraft"); }

            /**
             * Returns the version associated to this alias: modmenu (9.0.0)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getModmenu() { return getVersion("modmenu"); }

            /**
             * Returns the version associated to this alias: shadow (7.1.2)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getShadow() { return getVersion("shadow"); }

        /**
         * Returns the group of versions at versions.architectury
         */
        public ArchitecturyVersionAccessors getArchitectury() {
            return vaccForArchitecturyVersionAccessors;
        }

        /**
         * Returns the group of versions at versions.fabric
         */
        public FabricVersionAccessors getFabric() {
            return vaccForFabricVersionAccessors;
        }

        /**
         * Returns the group of versions at versions.mod
         */
        public ModVersionAccessors getMod() {
            return vaccForModVersionAccessors;
        }

        /**
         * Returns the group of versions at versions.modmuss
         */
        public ModmussVersionAccessors getModmuss() {
            return vaccForModmussVersionAccessors;
        }

        /**
         * Returns the group of versions at versions.overrides
         */
        public OverridesVersionAccessors getOverrides() {
            return vaccForOverridesVersionAccessors;
        }

        /**
         * Returns the group of versions at versions.quilt
         */
        public QuiltVersionAccessors getQuilt() {
            return vaccForQuiltVersionAccessors;
        }

        /**
         * Returns the group of versions at versions.release
         */
        public ReleaseVersionAccessors getRelease() {
            return vaccForReleaseVersionAccessors;
        }

        /**
         * Returns the group of versions at versions.yarn
         */
        public YarnVersionAccessors getYarn() {
            return vaccForYarnVersionAccessors;
        }

    }

    public static class ArchitecturyVersionAccessors extends VersionFactory  {

        public ArchitecturyVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: architectury.loom (1.5-SNAPSHOT)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getLoom() { return getVersion("architectury.loom"); }

            /**
             * Returns the version associated to this alias: architectury.plugin (3.4-SNAPSHOT)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getPlugin() { return getVersion("architectury.plugin"); }

    }

    public static class FabricVersionAccessors extends VersionFactory  {

        public FabricVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: fabric.loader (0.15.7)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getLoader() { return getVersion("fabric.loader"); }

    }

    public static class ModVersionAccessors extends VersionFactory  {

        public ModVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: mod.version (1.0-alpha)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getVersion() { return getVersion("mod.version"); }

    }

    public static class ModmussVersionAccessors extends VersionFactory  {

        public ModmussVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: modmuss.publish (0.5.1)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getPublish() { return getVersion("modmuss.publish"); }

    }

    public static class OverridesVersionAccessors extends VersionFactory  {

        public OverridesVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: overrides.curseforge (mc)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getCurseforge() { return getVersion("overrides.curseforge"); }

            /**
             * Returns the version associated to this alias: overrides.modrinth (mc)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getModrinth() { return getVersion("overrides.modrinth"); }

    }

    public static class QuiltVersionAccessors extends VersionFactory  {

        public QuiltVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: quilt.loader (0.17.6)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getLoader() { return getVersion("quilt.loader"); }

    }

    public static class ReleaseVersionAccessors extends VersionFactory  {

        public ReleaseVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: release.type (release)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getType() { return getVersion("release.type"); }

    }

    public static class YarnVersionAccessors extends VersionFactory  {

        public YarnVersionAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Returns the version associated to this alias: yarn.mappings (1.20.4+build.3)
             * If the version is a rich version and that its not expressible as a
             * single version string, then an empty string is returned.
             * This version was declared in catalog libs.versions.toml
             */
            public Provider<String> getMappings() { return getVersion("yarn.mappings"); }

    }

    /**
     * @deprecated Will be removed in Gradle 9.0.
     */
    @Deprecated
    public static class BundleAccessors extends BundleFactory {

        public BundleAccessors(ObjectFactory objects, ProviderFactory providers, DefaultVersionCatalog config, ImmutableAttributesFactory attributesFactory, CapabilityNotationParser capabilityNotationParser) { super(objects, providers, config, attributesFactory, capabilityNotationParser); }

    }

    public static class PluginAccessors extends PluginFactory {
        private final ArchitecturyPluginAccessors paccForArchitecturyPluginAccessors = new ArchitecturyPluginAccessors(providers, config);
        private final ModmussPluginAccessors paccForModmussPluginAccessors = new ModmussPluginAccessors(providers, config);

        public PluginAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Creates a plugin provider for shadow to the plugin id 'com.github.johnrengelman.shadow'
             * This plugin was declared in catalog libs.versions.toml
             */
            public Provider<PluginDependency> getShadow() { return createPlugin("shadow"); }

        /**
         * Returns the group of plugins at plugins.architectury
         */
        public ArchitecturyPluginAccessors getArchitectury() {
            return paccForArchitecturyPluginAccessors;
        }

        /**
         * Returns the group of plugins at plugins.modmuss
         */
        public ModmussPluginAccessors getModmuss() {
            return paccForModmussPluginAccessors;
        }

    }

    public static class ArchitecturyPluginAccessors extends PluginFactory {

        public ArchitecturyPluginAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Creates a plugin provider for architectury.loom to the plugin id 'dev.architectury.loom'
             * This plugin was declared in catalog libs.versions.toml
             */
            public Provider<PluginDependency> getLoom() { return createPlugin("architectury.loom"); }

            /**
             * Creates a plugin provider for architectury.plugin to the plugin id 'architectury-plugin'
             * This plugin was declared in catalog libs.versions.toml
             */
            public Provider<PluginDependency> getPlugin() { return createPlugin("architectury.plugin"); }

    }

    public static class ModmussPluginAccessors extends PluginFactory {

        public ModmussPluginAccessors(ProviderFactory providers, DefaultVersionCatalog config) { super(providers, config); }

            /**
             * Creates a plugin provider for modmuss.publish to the plugin id 'me.modmuss50.mod-publish-plugin'
             * This plugin was declared in catalog libs.versions.toml
             */
            public Provider<PluginDependency> getPublish() { return createPlugin("modmuss.publish"); }

    }

}
