package bioresultats.rest.resources.asm;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import bioresultats.core.models.entities.Blog;
import bioresultats.rest.mvc.AccountController;
import bioresultats.rest.mvc.BlogController;
import bioresultats.rest.resources.BlogResource;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

public class BlogResourceAsm extends
		ResourceAssemblerSupport<Blog, BlogResource> {
	public BlogResourceAsm() {
		super(BlogController.class, BlogResource.class);
	}

	@Override
	public BlogResource toResource(Blog blog) {
		BlogResource resource = new BlogResource();
		resource.setTitle(blog.getTitle());
		resource.add(linkTo(BlogController.class).slash(blog.getId())
				.withSelfRel());
		resource.add(linkTo(BlogController.class).slash(blog.getId())
				.slash("entries").withRel("entries"));
		if (blog.getOwner() != null)
			resource.add(linkTo(AccountController.class).slash(
					blog.getOwner().getId()).withRel("owner"));
		return resource;
	}
}