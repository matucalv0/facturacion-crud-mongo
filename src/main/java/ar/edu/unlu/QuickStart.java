package ar.edu.unlu;

import ar.edu.unlu.models.Cliente;
import ar.edu.unlu.models.Factura;
import ar.edu.unlu.models.Producto;
import ar.edu.unlu.services.ClienteService;
import ar.edu.unlu.services.FacturaService;
import ar.edu.unlu.services.ProductoService;
import ar.edu.unlu.view.MongoApp;
import ar.edu.unlu.view.ProductoView;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.model.Indexes;
import io.github.cdimascio.dotenv.Dotenv;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class QuickStart {
    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();
        String uri = dotenv.get("MONGODB_URI"); // cargo la uri desde el .env para que no quede expuesto en el código
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));


        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("facturacion-db").withCodecRegistry(pojoCodecRegistry);
            System.out.println("Conexión exitosa a MongoDB! Base de datos: " + database.getName());
            System.out.println("-------------------------------------------------------------------------------");
            //clientes
            MongoCollection<Cliente> clientesCollection = database.getCollection("clientes", Cliente.class);
            System.out.println("Colección 'clientes' obtenida con éxito. Documentos en la colección: " + clientesCollection.countDocuments());
            System.out.println("Verificando indices en " + clientesCollection.getNamespace() + " ...");
            clientesCollection.createIndex(Indexes.ascending("dni"), new IndexOptions().unique(true));
            ClienteService clienteService = new ClienteService(clientesCollection);

            //productos
            MongoCollection<Producto> productosCollection =
                    database.getCollection("productos", Producto.class);
            System.out.println(
                    "Colección 'productos' obtenida con éxito. Documentos en la colección: "
                            + productosCollection.countDocuments());
            productosCollection.createIndex(Indexes.ascending("idp"), new IndexOptions().unique(true));
            ProductoService productoService = new ProductoService(productosCollection);

           //facturas
            MongoCollection<Factura> facturasCollection = database.getCollection("facturas", Factura.class);
            System.out.println("Colección 'facturas' obtenida con éxito. Documentos en la colección: " + facturasCollection.countDocuments());
            facturasCollection.createIndex(Indexes.ascending("numero"), new IndexOptions().unique(true));
            FacturaService facturaService = new FacturaService(facturasCollection);

            MongoApp app = new MongoApp(clienteService, productoService, facturaService);
            app.run();

        } catch ( Exception e) {
                System.err.println("Ocurrió un error conectando a MongoDB: " + e.getMessage());
        }



    }
}
