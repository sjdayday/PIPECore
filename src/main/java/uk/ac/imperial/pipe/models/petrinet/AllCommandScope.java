package uk.ac.imperial.pipe.models.petrinet;

import uk.ac.imperial.pipe.exceptions.IncludeException;



public class AllCommandScope implements IncludeHierarchyCommandScope {

	private IncludeHierarchy includes;

	public AllCommandScope(IncludeHierarchy includes) {
		this.includes = includes; 
	}

	@Override
	public <T> Result<T> execute(IncludeHierarchyCommand<T> command) throws IncludeException {
		return includes.all(command);
	}

}