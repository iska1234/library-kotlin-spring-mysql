package pe.idat.util;

import pe.idat.entity.Cliente;
import pe.idat.mapper.ClienteMapper;

import java.util.ArrayList;
import java.util.Collection;

public class ConvertMapper {

    public static Collection<ClienteMapper> toCliente(Collection<Cliente> collection){
        Collection<ClienteMapper> mapper = new ArrayList<>();
        for (Cliente cliente:collection){
            ClienteMapper clienteMapper = new ClienteMapper(cliente);
            mapper.add(clienteMapper);
        }
        return mapper;
    }
}
