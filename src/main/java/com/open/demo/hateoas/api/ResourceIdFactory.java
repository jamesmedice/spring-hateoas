package com.open.demo.hateoas.api;

import com.open.demo.hateoas.domain.Author;
import com.open.demo.hateoas.domain.Book;
import com.open.demo.hateoas.domain.Publisher;

// Encapsulate the logic to get the resource ID out of the domain entity
public abstract class ResourceIdFactory {

   public static String getId(Author author) {
      return author.getHandle();
   }
   
   public static String getId(Book book) {
      return book.getIsbn();
   }
   
   public static String getId(Publisher publisher) {
      return publisher.getId().toString();
   }
}
