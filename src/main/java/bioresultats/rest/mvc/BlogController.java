package bioresultats.rest.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import bioresultats.core.models.entities.Blog;
import bioresultats.core.services.util.BlogEntryList;
import bioresultats.core.models.entities.BlogEntry;
import bioresultats.core.services.IBlogService;
import bioresultats.core.services.exceptions.BlogNotFoundException;
import bioresultats.core.services.util.BlogList;
import bioresultats.rest.exceptions.NotFoundException;
import bioresultats.rest.resources.BlogEntryListResource;
import bioresultats.rest.resources.BlogEntryResource;
import bioresultats.rest.resources.BlogListResource;
import bioresultats.rest.resources.BlogResource;
import bioresultats.rest.resources.asm.BlogEntryListResourceAsm;
import bioresultats.rest.resources.asm.BlogEntryResourceAsm;
import bioresultats.rest.resources.asm.BlogListResourceAsm;
import bioresultats.rest.resources.asm.BlogResourceAsm;

import java.net.URI;

/**
 * Created by Chris on 6/28/14.
 */
@Controller
@RequestMapping("/rest/blogs")
public class BlogController {
	
	private IBlogService blogService;

	@Autowired
	public BlogController(IBlogService blogService) {
		this.blogService = blogService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<BlogListResource> findAllBlogs() {
		BlogList blogList = blogService.findAllBlogs();
		BlogListResource blogListRes = new BlogListResourceAsm()
				.toResource(blogList);
		return new ResponseEntity<BlogListResource>(blogListRes, HttpStatus.OK);
	}

	@RequestMapping(value = "/{blogId}", method = RequestMethod.GET)
	public ResponseEntity<BlogResource> getBlog(@PathVariable Long blogId) {
		Blog blog = blogService.findBlog(blogId);
		BlogResource res = new BlogResourceAsm().toResource(blog);
		return new ResponseEntity<BlogResource>(res, HttpStatus.OK);
	}

	@RequestMapping(value = "/{blogId}/blog-entries", method = RequestMethod.POST)
	public ResponseEntity<BlogEntryResource> createBlogEntry(
			@PathVariable Long blogId,
			@RequestBody BlogEntryResource sentBlogEntry) {
		BlogEntry createdBlogEntry = null;
		try {
			createdBlogEntry = blogService.createBlogEntry(blogId,
					sentBlogEntry.toBlogEntry());
			BlogEntryResource createdResource = new BlogEntryResourceAsm()
					.toResource(createdBlogEntry);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(URI.create(createdResource.getLink("self")
					.getHref()));
			return new ResponseEntity<BlogEntryResource>(createdResource,
					headers, HttpStatus.CREATED);
		} catch (BlogNotFoundException e) {
			throw new NotFoundException(e);
		}
	}

	@RequestMapping(value = "/{blogId}/blog-entries")
	public ResponseEntity<BlogEntryListResource> findAllBlogEntries(
			@PathVariable Long blogId) {
		try {
			BlogEntryList list = blogService.findAllBlogEntries(blogId);
			BlogEntryListResource res = new BlogEntryListResourceAsm()
					.toResource(list);
			return new ResponseEntity<BlogEntryListResource>(res, HttpStatus.OK);
		} catch (BlogNotFoundException exception) {
			throw new NotFoundException(exception);
		}
	}
}