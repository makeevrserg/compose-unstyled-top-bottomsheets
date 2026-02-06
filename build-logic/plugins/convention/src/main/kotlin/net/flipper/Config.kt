package net.flipper

import net.flipper.property.GradlePropertyValue
import net.flipper.property.PropertyValue
import net.flipper.property.asCached
import net.flipper.property.exception.PropertyValueNotPresentException
import net.flipper.property.mapper.DeveloperMapper
import net.flipper.property.model.ProjectInfo
import net.flipper.property.model.PublishInfo
import org.gradle.api.Project

object Config {
    val Project.requirePublishInfo: PublishInfo
        get() = PublishInfo(
            libraryName = baseGradleProperty("publish.name").getValue().getOrThrow(),
            description = baseGradleProperty("publish.description").getValue().getOrThrow(),
            gitHubOrganization = baseGradleProperty("publish.repo.org").getValue().getOrThrow(),
            gitHubName = baseGradleProperty("publish.repo.name").getValue().getOrThrow(),
            license = baseGradleProperty("publish.license").getValue().getOrThrow(),
            publishGroupId = baseGradleProperty("publish.groupId").getValue().getOrThrow(),
        )

    val Project.requireProjectInfo: ProjectInfo
        get() = ProjectInfo(
            name = baseGradleProperty("project.name").getValue().getOrThrow(),
            group = baseGradleProperty("project.group").getValue().getOrThrow(),
            versionString = baseGradleProperty("project.version.string").getValue().getOrThrow(),
            description = baseGradleProperty("project.description").getValue().getOrThrow(),
            url = baseGradleProperty("project.url").getValue().getOrThrow(),
            developersList = baseGradleProperty("project.developers").getValue().getOrThrow()
                .let(DeveloperMapper::parseDevelopers),
        )
}

private const val BASE_PREFIX = "makeevrserg"

fun Project.baseGradleProperty(path: String): PropertyValue {
    return GradlePropertyValue(this, "$BASE_PREFIX.$path")
        .asCached(rootProject.extensions)
}
