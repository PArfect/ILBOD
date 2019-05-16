package com.ilbod.testsValidations;

import com.ilbod.detection.carte.Lieu;

import org.junit.Assert;
import org.junit.Test;

public class TestGestionCarte {
    @Test
    public void TestconstructeurTest2() {
        Lieu lieu = new Lieu("lieu");

        Assert.assertNotNull(lieu);
        Assert.assertNotNull(lieu.getObjets());
    }
}
