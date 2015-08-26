package com.pmi.tutor.dao;

import com.pmi.tutor.domain.TemporaryLink;

public interface TemporaryLinkDAO extends GenericDAO<TemporaryLink>{

	TemporaryLink fetchByLink(String link);

}
