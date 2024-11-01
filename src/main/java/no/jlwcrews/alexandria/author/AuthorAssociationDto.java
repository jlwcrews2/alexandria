package no.jlwcrews.alexandria.author;

public record AuthorAssociationDto(
        long bookId,
        long authorId
) {
}
