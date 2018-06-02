/**
 */
package org.palladiosimulator.loadbalancingaction.loadbalancing.tests;

import java.io.File;
import java.io.IOException;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;

import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import org.eclipse.emf.ecore.util.Diagnostician;

import org.palladiosimulator.loadbalancingaction.loadbalancing.LoadbalancingAction;
import org.palladiosimulator.loadbalancingaction.loadbalancing.LoadbalancingFactory;
import org.palladiosimulator.loadbalancingaction.loadbalancing.LoadbalancingPackage;

import org.palladiosimulator.loadbalancingaction.loadbalancing.util.LoadbalancingResourceFactoryImpl;

/**
 * <!-- begin-user-doc -->
 * A sample utility for the '<em><b>loadbalancing</b></em>' package.
 * <!-- end-user-doc -->
 * @generated
 */
public class LoadbalancingExample {
    /**
     * <!-- begin-user-doc -->
     * Load all the argument file paths or URIs as instances of the model.
     * <!-- end-user-doc -->
     * @param args the file paths or URIs.
     * @generated
     */
    public static void main(final String[] args) {
        // Create a resource set to hold the resources.
        //
        final ResourceSet resourceSet = new ResourceSetImpl();

        // Register the appropriate resource factory to handle all file extensions.
        //
        resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
                .put(Resource.Factory.Registry.DEFAULT_EXTENSION, new LoadbalancingResourceFactoryImpl());

        // Register the package to ensure it is available during loading.
        //
        resourceSet.getPackageRegistry().put(LoadbalancingPackage.eNS_URI, LoadbalancingPackage.eINSTANCE);

        // If there are no arguments, emit an appropriate usage message.
        //
        if (args.length == 0) {
            System.out.println("Enter a list of file paths or URIs that have content like this:");
            try {
                final Resource resource = resourceSet.createResource(URI.createURI("http:///My.loadbalancing"));
                final LoadbalancingAction root = LoadbalancingFactory.eINSTANCE.createLoadbalancingAction();
                resource.getContents().add(root);
                resource.save(System.out, null);
            } catch (final IOException exception) {
                exception.printStackTrace();
            }
        } else {
            // Iterate over all the arguments.
            //
            for (int i = 0; i < args.length; ++i) {
                // Construct the URI for the instance file.
                // The argument is treated as a file path only if it denotes an existing file.
                // Otherwise, it's directly treated as a URL.
                //
                final File file = new File(args[i]);
                final URI uri = file.isFile() ? URI.createFileURI(file.getAbsolutePath()) : URI.createURI(args[i]);

                try {
                    // Demand load resource for this file.
                    //
                    final Resource resource = resourceSet.getResource(uri, true);
                    System.out.println("Loaded " + uri);

                    // Validate the contents of the loaded resource.
                    //
                    for (final EObject eObject : resource.getContents()) {
                        final Diagnostic diagnostic = Diagnostician.INSTANCE.validate(eObject);
                        if (diagnostic.getSeverity() != Diagnostic.OK) {
                            printDiagnostic(diagnostic, "");
                        }
                    }
                } catch (final RuntimeException exception) {
                    System.out.println("Problem loading " + uri);
                    exception.printStackTrace();
                }
            }
        }
    }

    /**
     * <!-- begin-user-doc -->
     * Prints diagnostics with indentation.
     * <!-- end-user-doc -->
     * @param diagnostic the diagnostic to print.
     * @param indent the indentation for printing.
     * @generated
     */
    protected static void printDiagnostic(final Diagnostic diagnostic, final String indent) {
        System.out.print(indent);
        System.out.println(diagnostic.getMessage());
        for (final Diagnostic child : diagnostic.getChildren()) {
            printDiagnostic(child, indent + "  ");
        }
    }

} //LoadbalancingExample
