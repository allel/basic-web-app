package tutorial.rest.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import tutorial.core.models.entities.BlogEntry;
import tutorial.core.services.IBlogEntryService;
import tutorial.rest.resources.BlogEntryResource;
import tutorial.rest.resources.asm.BlogEntryResourceAsm;

@Controller
@RequestMapping("/rest/blog-entries")
public class BlogEntryController {
	
	private IBlogEntryService service;

	@Autowired
	public BlogEntryController(IBlogEntryService service) {
		this.service = service;
	}

	@RequestMapping("/testold")
	public ResponseEntity<BlogEntry> testold() {
		BlogEntry entry = new BlogEntry();
		entry.setTitle("TITLE");

		return new ResponseEntity<BlogEntry>(entry, HttpStatus.OK);
	}

	@RequestMapping(value = "/test", method = RequestMethod.POST)
	public @ResponseBody BlogEntry test(@RequestBody BlogEntry entry) {
		return entry;
	}

	@RequestMapping(value = "/{blogEntryId}", method = RequestMethod.GET)
	public ResponseEntity<BlogEntryResource> getBlogEntry(
			@PathVariable Long blogEntryId) {
		BlogEntry entry = service.findBlogEntry(blogEntryId);
		if (entry != null) {
			BlogEntryResource res = new BlogEntryResourceAsm()
					.toResource(entry);
			return new ResponseEntity<BlogEntryResource>(res, HttpStatus.OK);
		} else {
			return new ResponseEntity<BlogEntryResource>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/{blogEntryId}", method = RequestMethod.DELETE)
	public ResponseEntity<BlogEntryResource> deleteBlogEntry(
			@PathVariable Long blogEntryId) {
		BlogEntry entry = service.deleteBlogEntry(blogEntryId);
		if (entry != null) {
			BlogEntryResource res = new BlogEntryResourceAsm()
					.toResource(entry);
			return new ResponseEntity<BlogEntryResource>(res, HttpStatus.OK);
		} else {
			return new ResponseEntity<BlogEntryResource>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/{blogEntryId}", method = RequestMethod.PUT)
	public ResponseEntity<BlogEntryResource> updateBlogEntry(
			@PathVariable Long blogEntryId,
			@RequestBody BlogEntryResource sentBlogEntry) {
		BlogEntry updatedEntry = service.updateBlogEntry(blogEntryId,
				sentBlogEntry.toBlogEntry());
		if (updatedEntry != null) {
			BlogEntryResource res = new BlogEntryResourceAsm()
					.toResource(updatedEntry);
			return new ResponseEntity<BlogEntryResource>(res, HttpStatus.OK);
		} else {
			return new ResponseEntity<BlogEntryResource>(HttpStatus.NOT_FOUND);
		}
	}
}
