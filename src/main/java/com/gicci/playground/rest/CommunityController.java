package com.gicci.playground.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gicci.playground.exception.RecordNotFound;
import com.gicci.playground.model.Community;
import com.gicci.playground.model.CommunityAddress;
import com.gicci.playground.model.Fellow;
import com.gicci.playground.service.CommunityService;

@RestController
@RequestMapping("/community")
public class CommunityController {

	@Autowired
	private CommunityService communityService;
	
	@RequestMapping(value = "/view/{id}")
	public Community viewCommunity(@PathVariable Long id) throws RecordNotFound {
		return communityService.findById(id);
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public List<Community> getCommunityList() {
		return communityService.findAll();
	}
	
	@RequestMapping(value = "/fellow/list", method = RequestMethod.GET)
	public List<Fellow> getFellowByCommunity(@RequestParam(name = "communityId", required = true) Long id) throws RecordNotFound {
		return communityService.getFellowListByCommunity(id);
	}
	
	@RequestMapping(value = "/address/list", method = RequestMethod.GET)
	public List<CommunityAddress> getCommunityAddresses(@RequestParam(name = "communityId", required = true) Long id) throws RecordNotFound {
		return communityService.getCommunityAddress(id);
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public Community createCommunity(@RequestBody Community community) {
		return communityService.create(community);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public Community updateCommunity(@RequestBody Community community) {
		return communityService.update(community);
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public Community deleteCommunity(@PathVariable("id") Long id) {
		return communityService.delete(id);
	}
}
