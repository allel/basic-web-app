package bioresultats.core.services.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bioresultats.core.models.entities.BlogEntry;
import bioresultats.core.repositories.BlogEntryRepo;
import bioresultats.core.services.IBlogEntryService;

@Service
@Transactional
public class BlogEntryServiceImpl implements IBlogEntryService {

	@Autowired
	private BlogEntryRepo entryRepo;

	@Override
	public BlogEntry findBlogEntry(Long id) {
		return entryRepo.findBlogEntry(id);
	}

	@Override
	public BlogEntry deleteBlogEntry(Long id) {
		return entryRepo.deleteBlogEntry(id);
	}

	@Override
	public BlogEntry updateBlogEntry(Long id, BlogEntry data) {
		return entryRepo.updateBlogEntry(id, data);
	}
}
