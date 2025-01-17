package com.revature.overcharge.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.overcharge.beans.Deck;
import com.revature.overcharge.beans.TechTag;
import com.revature.overcharge.repositories.TagRepo;

@Service
public class TagServiceImpl implements TagService {
	
	 private static final Logger log = LoggerFactory.getLogger(TagServiceImpl.class);
	
	@Autowired
	TagRepo tr;
	
	public TagServiceImpl(TagRepo tr2) {
		this.tr = tr2;
	}

	@Override
    public List<TechTag> getAllTags() {
        List<TechTag> tags = (List<TechTag>) tr.findAll();
  
        return tags;
    }

}
