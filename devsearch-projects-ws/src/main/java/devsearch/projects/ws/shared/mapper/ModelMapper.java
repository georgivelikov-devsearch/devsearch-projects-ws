package devsearch.projects.ws.shared.mapper;

public interface ModelMapper {
    public <D> D map(Object source, Class<D> destinationType);
}
