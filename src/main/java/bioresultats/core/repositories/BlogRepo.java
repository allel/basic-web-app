package bioresultats.core.repositories;

import java.util.List;

import bioresultats.core.models.entities.Blog;

public interface BlogRepo {
	public Blog createBlog(Blog data);

	public List<Blog> findAllBlogs();

	public Blog findBlog(Long id);

	public Blog findBlogByTitle(String title);

	public List<Blog> findBlogsByAccount(Long accountId);

}
