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

Provides for easier indexing the documents, targeting many similar fields per document. By moving this subset of data
into a **key-value sub-document**, we can use non-deterministic field names, add additional qualifiers to the
information, and more clearly state the relationship of the original field and value. When we use the Attribute Pattern,
we need fewer indexes, our queries become simpler to write, and our queries become faster.

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

---
**Bucket Pattern**

This pattern is particularly effective when working with Internet of Things (IoT), Real-Time Analytics, or Time-Series
data in general. By bucketing data together we make it easier to organize specific groups of data, increasing the
ability to discover historical trends or provide future forecasting and optimize our use of storage.

It's vital to find a compromise in **bucket size**. If we set size 50, then front can only query 50 measurements and
show client required measurements. The repository don't need to offer getting less than minimal bucket size.

_Why use:_

* reduce storage space
* index size savings,
* ability to use **pre-aggregated data** in our documents

_When use:_

* IoT, Real-Time Analytics, or Time-Series data in general

_Sample use:_

* Temperature measurements

---
**Outlier Pattern**

Tailoring your schema for the typical document or query, application performance will be optimized for those normal use
cases and the outliers will still be addressed.

It's possible to store outliers' data in separate collection .

_Why use:_

* Preventing **a few** documents or queries to determine an application's solution. Especially when that solution would
  not **be optimal for the majority** of use cases.

_When use:_
* Aa few documents which have to be handled different
 
_Sample use:_
* Books, movies: handling IT books vs LOTR book