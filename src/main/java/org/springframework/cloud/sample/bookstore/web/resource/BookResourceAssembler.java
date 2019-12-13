/*
 * Copyright 2002-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.cloud.sample.bookstore.web.resource;

import org.springframework.cloud.sample.bookstore.web.controller.BookController;
import org.springframework.cloud.sample.bookstore.web.model.Book;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

public class BookResourceAssembler {
	public BookResource toModel(Book book, String bookStoreId) {
		BookResource bookResource = new BookResource(book);
		bookResource.add(
				linkTo(BookController.class, bookStoreId)
						.slash(book.getId())
						.withSelfRel());

		return bookResource;
	}

	public List<BookResource> toCollectionModel(Collection<Book> books, String bookStoreId) {
		return books.stream()
				.map(book -> toModel(book, bookStoreId))
				.collect(Collectors.toCollection(() -> new ArrayList<>(books.size())));
	}
}
