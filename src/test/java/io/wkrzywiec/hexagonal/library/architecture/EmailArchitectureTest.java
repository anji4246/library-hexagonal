package io.wkrzywiec.hexagonal.library.architecture;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import io.wkrzywiec.hexagonal.library.email.EmailFacade;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClass;
import static com.tngtech.archunit.library.Architectures.onionArchitecture;

@AnalyzeClasses(packages = {"io.wkrzywiec.hexagonal.library.email"},
        importOptions = { ImportOption.DoNotIncludeTests.class })
public class EmailArchitectureTest {

    @ArchTest
    public static final ArchRule hexagonalArchInEmailDomain = onionArchitecture()
            .domainModels("io.wkrzywiec.hexagonal.library.email.model..")
            .domainServices("io.wkrzywiec.hexagonal.library.email..")
            .applicationServices("io.wkrzywiec.hexagonal.library.email.application..")
            .adapter("infrastructure", "io.wkrzywiec.hexagonal.library.email.infrastructure..");

    @ArchTest
    public static final ArchRule noSpringDependenciesInEmailFacade =
            noClass(EmailFacade.class)
                    .should()
                    .dependOnClassesThat()
                    .resideInAPackage("org.springframework..");
}
