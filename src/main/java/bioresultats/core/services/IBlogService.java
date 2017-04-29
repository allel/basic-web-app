package bioresultats.core.services;

import bioresultats.core.models.entities.Blog;
import bioresultats.core.models.entities.BlogEntry;
import bioresultats.core.services.util.BlogEntryList;
import bioresultats.core.services.util.BlogList;

public interface IBlogService {

	/**
	 * @param blogId
	 *            the id of the blog to add this BlogEntry to
	 * @param data
	 *            the BlogEntry containing the data to be used for creating the
	 *            new entity
	 * @return the created BlogEntry with a generated ID
	 * @throws bioresultats.core.services.exceptions.BlogNotFoundException
	 *             if the blog to add to cannot be found
	 */
	public BlogEntry createBlogEntry(Long blogId, BlogEntry data);

	public BlogList findAllBlogs();

	public BlogEntryList findAllBlogEntries(Long blogId); // findBlog all
															// associated blog
															// entries

	public Blog findBlog(Long id);

}
