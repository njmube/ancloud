package org.ancloud.presentation.context;

import java.util.HashMap;
import java.util.SortedMap;

import org.ancloud.domain.resource.Resource;

public class CodelistImpl extends HashMap<String,SortedMap<String,Resource>> implements Codelist {
	private static final long serialVersionUID = 3697105177619292620L;
}
