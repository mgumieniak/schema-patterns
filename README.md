**Polymorphic pattern**

The data stored about each comment does not need to be the same even though the documents are in the same collection.

_Why use:_

* Grouping documents together based on the queries we want to run (instead of separating the object across tables or
  collections) helps improve performance.

_When use:_

* We have queries to get all similar documents (all kind of comments)
* When all documents in a collection are of similar, but not identical.

_Sample use:_

* Products
---
**Attribute Pattern**

Provides for easier indexing the documents, targeting many similar fields per document. By moving
this subset of data into a **key-value sub-document**, we can use non-deterministic field names, add additional qualifiers
to the information, and more clearly state the relationship of the original field and value. When we use the Attribute
Pattern, we need fewer indexes, our queries become simpler to write, and our queries become faster.

_Why use:_

* Easier indexing the documents, targeting many similar fields per document.
 
_When use:_

* We have big documents with many similar fields which means the same but in different units (bottle units) or describe
  the common characteristics (film release date)
* The fields we need to sort on are only found in a small subset of documents
* For user-defined fields, when searched by them are required

_Sample use:_
Products:  clothing, may have sizes that are expressed in small, medium, or large. Other products in the same collection
may be expressed in volume. Yet others may be expressed in physical dimensions or weight.

