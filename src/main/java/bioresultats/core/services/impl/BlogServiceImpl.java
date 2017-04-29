package bioresultats.core.services.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bioresultats.core.models.entities.Blog;
import bioresultats.core.models.entities.BlogEntry;
import bioresultats.core.repositories.BlogEntryRepo;
import bioresultats.core.repositories.BlogRepo;
import bioresultats.core.services.IBlogService;
import bioresultats.core.services.exceptions.BlogNotFoundException;
import bioresultats.core.services.util.BlogEntryList;
import bioresultats.core.services.util.BlogList;

@Service
@Transactional
public class BlogServiceImpl implements IBlogService {

	@Autowired
	private BlogRepo blogRepo;
	@Autowired
	private BlogEntryRepo entryRepo;

	@Override
	public BlogEntry createBlogEntry(Long blogId, BlogEntry data) {
		Blog blog = blogRepo.findBlog(blogId);
		if (blog == null) {
			throw new BlogNotFoundException();
		}
		BlogEntry entry = entryRepo.createBlogEntry(data);
		entry.setBlog(blog);
		return entry;
	}

	@Override
	public BlogList findAllBlogs() {
		return new BlogList(blogRepo.findAllBlogs());
	}

	@Override
	public BlogEntryList findAllBlogEntries(Long blogId) {
		Blog blog = blogRepo.findBlog(blogId);
		if (blog == null) {
			throw new BlogNotFoundException();
		}
		return new BlogEntryList(blogId, entryRepo.findByBlogId(blogId));
	}

	@Override
	public Blog findBlog(Long id) {
		return blogRepo.findBlog(id);
	}

}
